package com.team5115.statemachines;

public class AutoGearCenter extends StateMachineBase {
	
	/**
	 * This state machine contains the routine for the gear path from the center of the autonomous section
	 */
	
	public static final int INIT = 1;
	public static final int DRIVING = 2;
	public static final int AIMING = 3;
	
	AutoDrive firstleg;
	AutoDrive secondleg;
	AimGear ag;
	
	public AutoGearCenter() {
		firstleg = new AutoDrive(5, 10, 6, 0, 0);
		secondleg = new AutoDrive(2.5, 5, 3, 0, 0);
		ag = new AimGear();
	}
	
	public void update() {
		switch (state) {
		case INIT:
			ag.setState(AimGear.START);
			firstleg.setState(AutoDrive.START);
			secondleg.setState(AutoDrive.START);
			setState(DRIVING);
			break;
			
		case DRIVING:
			firstleg.update();
			if (firstleg.isFinished()) {
				firstleg.setState(AutoDrive.STOP);
				setState(AIMING);
			}
			break;
			
		case AIMING:
			secondleg.update();
			ag.update();
			if (secondleg.isFinished()) {
				secondleg.setState(AutoDrive.STOP);
			}
			break;
		}
	}

}