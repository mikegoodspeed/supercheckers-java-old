package com.mikegoodspeed.supercheckers.datastructures;


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
	 * @return the team
	 */
	public char getTeam() {
		return team;
	}

//	/**
//	 * Determines if the team is valid, where validity is determined by being equal to one of the
//	 * predefined Teams.
//	 * 
//	 * @return true if the team is valid, false otherwise
//	 */
//	public boolean isValid() {
//		return TEAM1.equals(this) || TEAM2.equals(this) || EMPTY.equals(this);
//	}

	@Override
	public String toString() {
		return new String(new char[] { team });
	}
}
