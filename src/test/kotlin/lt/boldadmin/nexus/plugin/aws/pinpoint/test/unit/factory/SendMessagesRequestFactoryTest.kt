package lt.boldadmin.nexus.plugin.aws.pinpoint.test.unit.factory

import com.amazonaws.services.pinpoint.model.MessageRequest
import com.amazonaws.services.pinpoint.model.SendMessagesRequest
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import lt.boldadmin.nexus.plugin.aws.pinpoint.factory.MessageRequestFactory
import lt.boldadmin.nexus.plugin.aws.pinpoint.factory.SendMessagesRequestFactory
import lt.boldadmin.nexus.plugin.aws.pinpoint.provider.AwsPinpointAppIdProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SendMessagesRequestFactoryTest {

    @Test
    fun `Creates send message request`() {
        val fromPhoneNumber = "fromPhoneNumber"
        val toPhoneNumber = "toPhoneNumber"
        val message = "message"
        val applicationId = "applicationId"

        val messageRequestFactoryStub: MessageRequestFactory = mock()
        val messageRequestDummy: MessageRequest = mock()
        val awsPinpointAppIdProviderStub: AwsPinpointAppIdProvider = mock()

        val expectedRequest = SendMessagesRequest()
            .withMessageRequest(messageRequestDummy)
            .withApplicationId(applicationId)

        doReturn(applicationId).`when`(awsPinpointAppIdProviderStub).provide()
        doReturn(messageRequestDummy).`when`(messageRequestFactoryStub).create(fromPhoneNumber, toPhoneNumber, message)

        val actualRequest = SendMessagesRequestFactory(messageRequestFactoryStub, awsPinpointAppIdProviderStub)
            .create(fromPhoneNumber, toPhoneNumber, message)

        assertEquals(expectedRequest, actualRequest)
    }
}
