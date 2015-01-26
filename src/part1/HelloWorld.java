package part1;

import rp.systems.RobotProgrammingDemo;
import rp.config.RobotConfigs;
import rp.config.WheeledRobotConfiguration;
import rp.systems.RobotProgrammingDemo;
import rp.systems.WheeledRobotSystem;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class HelloWorld extends rp.systems.RobotProgrammingDemo {

	private final WheeledRobotSystem robot;

	public HelloWorld(WheeledRobotSystem robot) {
		this.robot = robot;
	}

	public void run(){
		RegulatedMotor leftWheel = robot.getConfig().getLeftWheel();
		RegulatedMotor rightWheel = robot.getConfig().getRightWheel();
		//DifferentialPilot pilot = robot.getPilot();
		LCD.drawString("Hello World!", 3, 4);
		Delay.msDelay(5000);
		Button.waitForAnyPress();		
		leftWheel.forward();		
		
		while (m_run) {
			rightWheel.backward();
			Button.waitForAnyPress();
			rightWheel.forward();
			Button.waitForAnyPress();
		}
			
	}
	
	public static void main(String[] args) {
		Button.waitForAnyPress();
		WheeledRobotSystem robot = new WheeledRobotSystem(RobotConfigs.BERT_BOT);
		RobotProgrammingDemo demo = new HelloWorld(robot);
		demo.run();
		
	}

}
