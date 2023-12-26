Lucene Social media Tokenizer
========================

This package provides a Lucene tokenizer and filter for social media data.

This s a fork from [wetneb/lucene-twitter](https://github.com/wetneb/lucene-twitter).

I have made the required changes to support Solr 9.4

* The `TwitterTokenizer` respects Twitter usernames and hashtags as single tokens (including
  the `@` and `#` signs). It is based on Lucene's `ClassicTokenizer` and behaves identically
  otherwise.
* The `TwitterLowercaseFilter` lowercases all social media usernames and hashtags, as they are
  case-insensitive.

Installing
----------

Create a `server/solr/lib` folder in your Solr install if it does not exist already,
and download there [the .jar for this plugin](https://github.com/dangelm/lucene-social-media/blob/master/release/lucene-twitter-0.0.2.jar).

Usage
-----

In a Solr schema, you can analyze fields using the tokenizer and filter, for instance like this:
```xml
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

