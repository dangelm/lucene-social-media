Lucene Twitter Tokenizer
========================

[![Build Status](https://travis-ci.org/wetneb/lucene-twitter.svg?branch=master)](https://travis-ci.org/wetneb/lucene-twitter)

This package provides a Lucene tokenizer and filter for Twitter data.
* The `TwitterTokenizer` respects Twitter usernames and hashtags as single tokens (including
  the `@` and `#` signs). It is based on Lucene's `ClassicTokenizer` and behaves identically
  otherwise.
* The `TwitterLowercaseFilter` lowercases all Twitter usernames and hashtags, as they are
  case-insensitive.

Installing
----------

Create a `server/solr/lib` folder in your Solr install if it does not exist already,
and download there [the .jar for this plugin](https://github.com/wetneb/lucene-twitter/releases/download/v0.0.1/lucene-twitter-0.0.1.jar).

Usage
-----

In a Solr schema, you can analyze fields using the tokenizer and filter, for instance like this:
```
    <fieldType name="text_twitter" class="solr.TextField" positionIncrementGap="100" multiValued="true">
      <analyzer type="index">
        <tokenizer class="org.opentapioca.analysis.twitter.TwitterTokenizerFactory" />
        <filter class="org.opentapioca.analysis.twitter.TwitterLowercaseFilterFactory" />
      </analyzer>
      <analyzer type="query">
        <tokenizer class="org.opentapioca.analysis.twitter.TwitterTokenizerFactory" />
        <filter class="org.opentapioca.analysis.twitter.TwitterLowercaseFilterFactory" />
      </analyzer>
    </fieldType>
```

Released under the Apache-2.0 license.

