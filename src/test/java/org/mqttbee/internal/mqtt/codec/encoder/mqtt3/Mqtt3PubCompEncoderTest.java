/*
 * Copyright 2018 The MQTT Bee project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.mqttbee.internal.mqtt.codec.encoder.mqtt3;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;
import org.mqttbee.internal.mqtt.codec.encoder.MqttMessageEncoders;
import org.mqttbee.internal.mqtt.message.publish.pubcomp.MqttPubComp;
import org.mqttbee.internal.mqtt.message.publish.pubcomp.mqtt3.Mqtt3PubCompView;
import org.mqttbee.mqtt.mqtt3.message.Mqtt3MessageType;

class Mqtt3PubCompEncoderTest extends AbstractMqtt3EncoderTest {

    Mqtt3PubCompEncoderTest() {
        super(new MqttMessageEncoders() {{
            encoders[Mqtt3MessageType.PUBCOMP.getCode()] = new Mqtt3PubCompEncoder();
        }}, true);
    }

    @Test
    void matchesPaho() throws MqttException {
        final int id = 42;
        final MqttPubComp beeMessage = Mqtt3PubCompView.delegate(id);
        final org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp pahoMessage =
                new org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp(id);

        encode(beeMessage, bytesOf(pahoMessage));
    }
}