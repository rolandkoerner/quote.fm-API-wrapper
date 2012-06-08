Quote.fm API Wrapper
====================

This is a small wrapper for the [quote.fm API][1], written in JAVA.

Dependencies
------------

The wrapper depends on [Apache HttpClient][2] and the [json.org JSON library][3].

Usage
-----

You can use the classes in `de.rolandkoerner.quotefmapi.entities`, called entities. Just use their static methods to recieve the data accordingly. 

For recieving a list of the newest articles in english posted on quote.fm, call `Article.list("en","time")`. 

Caching
-------

Every entity will be cached in memory to save bandwidth. The cached entities do not expire. You can control the cache sizes with setting `MAX_CACHE_SIZE` in the class `DAO`. The default setting is 200 entries per entity type.

Using another HTTP client
-------------------------

If you, for whatever reason, don't want to use the Apache HttpClient, you can implement the `IWebClient` class and call `APIWrapper.init` with your implementation before making API requests. `init` has to be called only once.

TODO
----

- add comments :)

[1]: http://quote.fm/labs/documentation/index
[2]: http://hc.apache.org/httpcomponents-client-ga/
[3]: https://github.com/douglascrockford/JSON-java