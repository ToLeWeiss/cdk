/* Copyright (C) 2012  Egon Willighagen <egonw@users.sf.net>
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
package org.openscience.cdk.dict;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @cdk.module test-dict
 */
public class EntryReactTest extends AbstractEntryTest {

    @BeforeEach
    void setTestClass() {
        super.setTestClass(new EntryReact("someID"));
    }

    @AfterEach
    void testTestedClass() {
        Assertions.assertTrue(super.getTestClass() instanceof EntryReact);
    }

    @Test
    @Override
    // customize because there is no constructor without any parameters
    void testID() {
        Entry entry = getTestClass();
        Assertions.assertEquals("someid", entry.getID());
        entry.setID("identifier");
        Assertions.assertEquals("identifier", entry.getID());
    }

}
