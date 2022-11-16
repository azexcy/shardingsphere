/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.test.sql.parser.internal.asserts.statement.distsql.rdl.alter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.distsql.parser.segment.DataSourceSegment;
import org.apache.shardingsphere.distsql.parser.statement.rdl.alter.AlterStorageUnitStatement;
import org.apache.shardingsphere.test.sql.parser.internal.asserts.SQLCaseAssertContext;
import org.apache.shardingsphere.test.sql.parser.internal.asserts.segment.distsql.DataSourceAssert;
import org.apache.shardingsphere.test.sql.parser.internal.jaxb.cases.domain.segment.impl.distsql.ExpectedDataSource;
import org.apache.shardingsphere.test.sql.parser.internal.jaxb.cases.domain.statement.distsql.rdl.alter.AlterStorageUnitStatementTestCase;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Alter storage unit statement assert.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AlterStorageUnitStatementAssert {
    
    /**
     * Assert alter storage unit statement is correct with expected parser result.
     *
     * @param assertContext assert context
     * @param actual actual alter storage unit statement
     * @param expected expected alter storage unit statement test case
     */
    public static void assertIs(final SQLCaseAssertContext assertContext, final AlterStorageUnitStatement actual, final AlterStorageUnitStatementTestCase expected) {
        if (null == expected) {
            assertNull(assertContext.getText("Actual statement should not exist."), actual);
        } else {
            assertNotNull(assertContext.getText("Actual statement should exist."), actual);
            assertDataSources(assertContext, actual.getStorageUnits(), expected.getDataSources());
        }
    }
    
    private static void assertDataSources(final SQLCaseAssertContext assertContext, final Collection<DataSourceSegment> actual, final List<ExpectedDataSource> expected) {
        if (null == expected) {
            assertNull(assertContext.getText("Actual storage unit should not exist."), actual);
        } else {
            assertNotNull(assertContext.getText("Actual storage unit should exist."), actual);
            assertThat(assertContext.getText(String.format("Actual storage unit size should be %s , but it was %s", expected.size(), actual.size())), actual.size(), is(expected.size()));
            int count = 0;
            for (DataSourceSegment actualDataSource : actual) {
                DataSourceAssert.assertIs(assertContext, actualDataSource, expected.get(count));
                count++;
            }
        }
    }
}
