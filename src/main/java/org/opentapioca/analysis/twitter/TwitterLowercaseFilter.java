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


import org.apache.lucene.analysis.CharacterUtils;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

/** Normalizes Twitter tokens (usernames, hashtags) extracted with {@link TwitterTokenizer} to lowercase. */

public class TwitterLowercaseFilter extends TokenFilter {

  /** Construct filtering <i>in</i>. */
  public TwitterLowercaseFilter(TokenStream in) {
    super(in);
  }

  private static final String TWITTER_USER_TYPE = TwitterTokenizer.TOKEN_TYPES[TwitterTokenizer.TWITTER_USER];
  private static final String TWITTER_HASHTAG_TYPE = TwitterTokenizer.TOKEN_TYPES[TwitterTokenizer.TWITTER_HASHTAG];

  // this filters uses attribute type
  private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class);
  private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
  
  /** Only lowercases Twitter usernames or hashtags. Leaves all other tokens untouched.
   */
  @Override
  public final boolean incrementToken() throws java.io.IOException {
    if (!input.incrementToken()) {
      return false;
    }

    final String type = typeAtt.type();

    if (type == TWITTER_USER_TYPE ||
        type == TWITTER_HASHTAG_TYPE) {
      CharacterUtils.toLowerCase(termAtt.buffer(), 0, termAtt.length());   
    }

    return true;
  }
}
