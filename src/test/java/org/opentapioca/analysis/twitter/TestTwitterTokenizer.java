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
package org.opentapioca.analysis.twitter;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.Tokenizer;
import org.opentapioca.analysis.twitter.TwitterTokenizer;

/** tests for classicanalyzer */
public class TestTwitterTokenizer extends BaseTokenStreamTestCase {

  private Analyzer a;
 
  @Override
  public void setUp() throws Exception {
    super.setUp();
    a = new Analyzer() {
        @Override
        protected TokenStreamComponents createComponents(String fieldName) {
          Tokenizer tokenizer = new TwitterTokenizer(newAttributeFactory());
          return new TokenStreamComponents(tokenizer);
        }
      };
  }
  
  @Override
  public void tearDown() throws Exception {
    a.close();
    super.tearDown();
  } 

  public void testEMailAddresses() throws Exception {
	    // email addresses, possibly with underscores, periods, etc
	    assertAnalyzesTo(a, "test@example.com", new String[]{"test@example.com"});
	    assertAnalyzesTo(a, "first.lastname@example.com", new String[]{"first.lastname@example.com"});
	    assertAnalyzesTo(a, "first_lastname@example.com", new String[]{"first_lastname@example.com"});
  }
  
  public void testTwitterUsernames() throws Exception {
	    // email addresses, possibly with underscores, periods, etc
	    assertAnalyzesTo(a, "@fhollande", new String[]{"@fhollande"});
	    assertAnalyzesTo(a, "I love @OpenRefine", new String[]{"I", "love", "@OpenRefine"});
	    assertAnalyzesTo(a, "@wikidata rocks", new String[]{"@wikidata", "rocks"});
  }
  
  public void testTwitterHashtags() throws Exception {
	    // email addresses, possibly with underscores, periods, etc
	    assertAnalyzesTo(a, "#FF", new String[]{"#FF"});
	    assertAnalyzesTo(a, "I love #OpenRefine", new String[]{"I", "love", "#OpenRefine"});
	    assertAnalyzesTo(a, "#wikidata rocks", new String[]{"#wikidata", "rocks"});
  } 
  
}
