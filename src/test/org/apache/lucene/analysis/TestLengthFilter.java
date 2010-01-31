package org.apache.lucene.analysis;

/**
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

import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

import java.io.StringReader;

public class TestLengthFilter extends BaseTokenStreamTestCase {
  
  public void testFilter() throws Exception {
    TokenStream stream = new WhitespaceTokenizer(Version.LUCENE_CURRENT, 
        new StringReader("short toolong evenmuchlongertext a ab toolong foo"));
    LengthFilter filter = new LengthFilter(stream, 2, 6);
    TermAttribute termAtt = filter.getAttribute(TermAttribute.class);

    assertTrue(filter.incrementToken());
    assertEquals("short", termAtt.term());
    assertTrue(filter.incrementToken());
    assertEquals("ab", termAtt.term());
    assertTrue(filter.incrementToken());
    assertEquals("foo", termAtt.term());
    assertFalse(filter.incrementToken());
  }

}
