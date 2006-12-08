package com.supercheckers.datastructures;

/**
 * A move performed during a turn.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class Team {
	private char team;
	
	public Team(char team) {
		this.team = team; 
	}

	/**
	 * @return the team
	 */
	public char getTeam() {
		return team;
	}

	/* (non-Javadoc)
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
			return team == other.team;
		}
	}
}
