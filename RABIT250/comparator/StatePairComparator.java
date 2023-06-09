/* Written by Yu-Fang Chen, Richard Mayr, and Chih-Duo Hong               */
/* Copyright (c) 2010                  	                                  */
/*                                                                        */
/* This program is free software; you can redistribute it and/or modify   */
/* it under the terms of the GNU General Public License as published by   */
/* the Free Software Foundation; either version 2 of the License, or      */
/* (at your option) any later version.                                    */
/*                                                                        */
/* This program is distributed in the hope that it will be useful,        */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of         */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          */
/* GNU General Public License for more details.                           */
/*                                                                        */
/* You should have received a copy of the GNU General Public License      */
/* along with this program; if not, write to the Free Software            */
/* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA*/

package comparator;



import java.util.Comparator;

import automata.FAState;
import datastructure.Pair;






public class StatePairComparator implements
		Comparator<Pair<FAState, FAState>> {

	public int compare(Pair<FAState,FAState> o1,
			Pair<FAState,FAState> o2) {
		if(o1.getLeft().compareTo(o2.getLeft())!=0){
			return o1.getLeft().compareTo(o2.getLeft());
		}else if(o1.getRight().compareTo(o2.getRight())!=0){ 
			return o1.getRight().compareTo(o2.getRight());
		}else{
			return 0;
		}
	}
}
