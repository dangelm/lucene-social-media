package org.opentapioca.analysis.twitter;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;

import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.opentapioca.analysis.twitter.TwitterLowercaseFilterFactory;
import org.opentapioca.analysis.twitter.TwitterTokenizer;

public class TestTwitterLowercaseFilter extends BaseTokenStreamTestCase {
  public void testNormalization() throws Exception {
	    Reader reader = new StringReader("#Wikidata Really Loves @OpenRefine");
	    Tokenizer tokenizer = new TwitterTokenizer();
	    tokenizer.setReader(reader);
	    TwitterLowercaseFilterFactory factory = new TwitterLowercaseFilterFactory(Collections.emptyMap());
	    TokenStream stream = factory.create(tokenizer);
	    assertTokenStreamContents(stream, new String[] { "#wikidata",  "Really", "Loves", "@openrefine" });
  }

}
