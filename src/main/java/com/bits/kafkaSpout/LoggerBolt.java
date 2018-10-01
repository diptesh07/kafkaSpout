package com.bits.kafkaSpout;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

public class LoggerBolt extends BaseBasicBolt{
	
	private static final long serialVersionUID = 1L; 

	public void execute(Tuple input, BasicOutputCollector collector) {
		// TODO Auto-generated method stub
		System.out.println("Inside the tuple");
		System.out.println(input.getString(0));
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("message"));
	}

}
