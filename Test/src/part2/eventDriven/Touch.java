package part2.eventDriven;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import part1.HelloWorld;
import rp.config.RobotConfigs;
import rp.systems.RobotProgrammingDemo;
import rp.systems.WheeledRobotSystem;

public class Touch extends RobotProgrammingDemo {

	private final WheeledRobotSystem robot;

	private void stopAndTurn(RegulatedMotor rightWheel, RegulatedMotor leftWheel, DifferentialPilot pilot) {
		rightWheel.stop();
		leftWheel.stop();
		pilot.travel(-100);
		pilot.rotate(90.0);
		rightWheel.forward();
		leftWheel.forward();
	}
	
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
		DifferentialPilot pilot = robot.getPilot();
		
		Sound.setVolume(Sound.VOL_MAX);
		leftWheel.forward();
		rightWheel.forward();
		
		SensorPort.S1.addSensorPortListener( new SensorPortListener()	
				{
					public void stateChanged(SensorPort s1, int i, int i1)
					{
						if (i - i1 > 30) {
							stopAndTurn(rightWheel, leftWheel, pilot);
						}
					}
				});
		SensorPort.S4.addSensorPortListener( new SensorPortListener()	
		{
			public void stateChanged(SensorPort s4, int i, int i1)
			{
				if (i - i1 > 30) {
					stopAndTurn(rightWheel, leftWheel, pilot);
				}
			}
		});
		
		while(m_run)
		{
			
		}
	}

}
