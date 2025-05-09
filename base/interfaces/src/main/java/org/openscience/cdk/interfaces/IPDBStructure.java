/* Copyright (C) 2006-2007  Miguel Rojas <miguel.rojas@uni-koeln.de>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.interfaces;

/**
 * Represents the idea of an chemical structure.
 *
 *
 * @author Miguel Rojas &lt;miguel.rojas@uni-koeln.de&gt;
 * @cdk.created 2006-11-20
 *
 * @cdk.keyword pdbpolymer
 */
public interface IPDBStructure extends ICDKObject {

    /**
     * get the ending Chain identifier of this structure.
     *
     * @return the ending Chain identifier of this structure
     */
    Character getEndChainID();

    /**
     * set the ending Chain identifier of this structure.
     *
     * @param endChainID  the ending Chain identifier of this structure
     */
    void setEndChainID(Character endChainID);

    /**
     * get the ending Code for insertion of residues of this structure.
     *
     * @return the ending Code for insertion of residues of this structure
     */
    Character getEndInsertionCode();

    /**
     * set the ending Code for insertion of residues of this structure.
     *
     * @param endInsertionCode  the ending Code for insertion of residues of this structure
     */
    void setEndInsertionCode(Character endInsertionCode);

    /**
     * get the ending sequence number of this structure.
     *
     * @return the ending sequence number of this structure
     */
    Integer getEndSequenceNumber();

    /**
     * set the ending sequence number of this structure.
     *
     * @param endSequenceNumber  the ending sequence number of this structure
     */
    void setEndSequenceNumber(Integer endSequenceNumber);

    /**
     * get start Chain identifier of this structure.
     *
     * @return the start Chain identifier of this structure
     */
    Character getStartChainID();

    /**
     * set the start Chain identifier of this structure.
     *
     * @param startChainID  the start Chain identifier of this structure
     */
    void setStartChainID(Character startChainID);

    /**
     * get start Code for insertion of residues of this structure.
     *
     * @return the start Code for insertion of residues of this structure
     */
    Character getStartInsertionCode();

    /**
     * set the start Chain identifier of this structure.
     *
     * @param startInsertionCode  the start Chain identifier of this structure
     */
    void setStartInsertionCode(Character startInsertionCode);

    /**
     * get the start sequence number of this structure.
     *
     * @return the start sequence number of this structure
     */
    Integer getStartSequenceNumber();

    /**
     * set the start sequence number of this structure.
     *
     * @param startSequenceNumber  the start sequence number of this structure
     */
    void setStartSequenceNumber(Integer startSequenceNumber);

    /**
     * get Structure Type of this structure.
     *
     * @return the Structure Type of this structure
     */
    String getStructureType();

    /**
     * set the Structure Type of this structure.
     *
     * @param structureType  the Structure Type of this structure
     */
    void setStructureType(String structureType);

}
