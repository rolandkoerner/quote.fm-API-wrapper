Quote.fm API Wrapper
====================

This is a small wrapper for the [quote.fm API][1], written in JAVA.

Dependencies
------------

The wrapper depends on [Apache HttpClient][2] and the [json.org JSON library][3].

Usage
-----

The entity classes are the cornerstone of the wrapper. Just use their static methods to recieve the data accordingly. 

### Example 

For recieving a list of the newest articles in english posted on quote.fm, call `Article.list("en","newest")`. 
	

[1]: http://quote.fm/labs/documentation/index
[2]: http://hc.apache.org/httpcomponents-client-ga/
[3]: https://github.com/douglascrockford/JSON-java