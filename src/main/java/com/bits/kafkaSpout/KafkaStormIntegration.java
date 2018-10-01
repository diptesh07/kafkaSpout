package com.bits.kafkaSpout;

import java.util.HashMap;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

public class KafkaStormIntegration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Hello World");
		
		final BrokerHosts brokers = new ZkHosts("localhost:2181");
		final String topic = "TestTopic";
		final String zkRoots = "";
		final String clientId = "storm-consumer";
		
		final SpoutConfig kafkaConf = new SpoutConfig(brokers, topic, zkRoots, clientId);
		kafkaConf.scheme = new SchemeAsMultiScheme(new StringScheme());

		// Build topology to consume message from kafka and print them on console
		final TopologyBuilder topologyBuilder = new TopologyBuilder();
		// Create KafkaSpout instance using Kafka configuration and add it to topology
		topologyBuilder.setSpout("kafka-spout", new KafkaSpout(kafkaConf), 4);
		//Route the output of Kafka Spout to Logger bolt to log messages consumed from Kafka
		topologyBuilder.setBolt("print-messages", new LoggerBolt()).globalGrouping("kafka-spout");
		
		Config config = new Config();
		
		// Submit topology to local cluster i.e. embedded storm instance in eclipse
		final LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("kafka-topology", config, topologyBuilder.createTopology());
	}

}
