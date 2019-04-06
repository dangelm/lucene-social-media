Lucene Twitter Tokenizer
========================

[![Build Status](https://travis-ci.org/wetneb/lucene-twitter.svg?branch=master)](https://travis-ci.org/wetneb/lucene-twitter)

This package provides a Lucene tokenizer and filter for Twitter data.
* The `TwitterTokenizer` respects Twitter usernames and hashtags as single tokens (including
  the `@` and `#` signs). It is based on Lucene's `ClassicTokenizer` and behaves identically
  otherwise.
* The `TwitterLowercaseFilter` lowercases all Twitter usernames and hashtags, as they are
  case-insensitive.

Apache-2.0 license.
