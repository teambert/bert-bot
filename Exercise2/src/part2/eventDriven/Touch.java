package part2.eventDriven;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.util.Delay;
import part1.HelloWorld;
import rp.config.RobotConfigs;
import rp.systems.RobotProgrammingDemo;
import rp.systems.WheeledRobotSystem;

public class Touch extends RobotProgrammingDemo {

	private final WheeledRobotSystem robot;

	
	public Touch(WheeledRobotSystem robot) {
		this.robot = robot;
	}

	public static void main(String[] args){
		Button.waitForAnyPress();
		WheeledRobotSystem robot = new WheeledRobotSystem(RobotConfigs.BERT_BOT);
		RobotProgrammingDemo demo = new Touch(robot);
		demo.run();
	}
	
	@Override
	public void run() {
		RegulatedMotor leftWheel = robot.getConfig().getLeftWheel();
		RegulatedMotor rightWheel = robot.getConfig().getRightWheel();
		
		leftWheel.forward();
		rightWheel.forward();
		
		SensorPort.S1.addSensorPortListener( new SensorPortListener()	
				{
					public void stateChanged(SensorPort s1, int i, int i1)
					{
						if (Math.abs(i - i1) > 2) {
							Sound.beep();
							Delay.msDelay(100);
						}
					}
				});
		
		while(m_run)
		{
			
		}
	}

}
