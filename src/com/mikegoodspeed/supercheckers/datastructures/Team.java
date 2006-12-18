package com.mikegoodspeed.supercheckers.datastructures;

import com.mikegoodspeed.supercheckers.constants.SCConst;

/**
 * A move performed during a turn.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class Team {

	private char team;

	/**
	 * Constructor to create new Team
	 * 
	 * @param team
	 */
	public Team(char team) {
		this.team = team;
	}

	/**
	 * @return the team
	 */
	public char getTeam() {
		return team;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			final Team other = (Team) obj;
			return getTeam() == other.getTeam();
		}
	}

	/**
	 * Determines if the team is valid, where validity is determined by being equal to one of the
	 * predefined Teams.
	 * 
	 * @return true if the team is valid, false otherwise
	 */
	public boolean isValid() {
		return SCConst.EMPTY.equals(this) || SCConst.TEAM1.equals(this)
		|| SCConst.TEAM2.equals(this);
	}

	public String toString() {
		return new String(new char[] { team });
	}
}
