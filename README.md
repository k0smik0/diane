# Diane #

Diane is an android framework for location-aware searching.

For example, a google places query is location-aware searching, because you need your geolocation.

So, Diane provides a set of interfaces which could help your implementation model.

Basically, all inherits from __Searcher__:

<pre> 
public interface Searcher<SearchState,SearchResult> { 
  public SearchState search() throws SearchException; 
  public SearchResult getSearchResult(); 
}
</pre>

so, we have the First obvious __LocationAwareSearcher__:

<pre> 
public interface LocationAwareSearcher<SearchState, SearchResult, LocationState> extends Searcher<SearchState,SearchResult> {
  public Location getLocation(); 
  public LocationState isInNewerLocation() throws LocationNotNewerStateException, LocationStateException; 
  public SearchState search() throws LocationAwareSearchException, SearchException; 
}
</pre>

Then, you can have a network searching or local (cached?) ones, and so:

combining LocationAwareSearcher with below __NetworkAwareSearcher__:

<pre>
public interface NetworkAwareSearcher<SearchState, SearchResult, NetworkState> extends Searcher<SearchState,SearchResult> {
  public NetworkState isConnected() throws NetworkStateException; 
  @Override
  public SearchState search() throws NetworkAwareSearchException, SearchException; 
}
</pre>

we have NetworkAwareByLocationSearcher:

<pre> 
public interface NetworkAwareByLocationSearcher<SearchState, SearchResult, NetworkState> extends NetworkAwareSearcher<SearchState, SearchResult, NetworkState>, ByLocationSearcher<SearchState, SearchResult> {}
</pre>

Finally, for local/cached search, we combine LocationAwareSearcher with __CacheAwareSearcher__

<pre>
public interface CacheAwareSearcher<SearchState, SearchResult> extends Searcher<SearchState, SearchResult> {}
</pre>

and we have __CacheAwareByLocationSearcher__

<pre>
public interface CacheAwareByLocationSearcher<SearchState, SearchResult> extends CacheAwareSearcher<SearchState, SearchResult>, ByLocationSearcher<SearchState, SearchResult> {
  @Override public SearchState search(Location location) throws LocationNullException;
}
</pre>

A base implementation for _LocationAwareSearcher_ is provided by __AbstractLocationAwareObserverSearcher__, which delegates to _Locator_ (see below) the location retrieving task.
As the name AbstractLocationAwareObserverSearcher is an observer (therefore Locator is an observable), but it also implements _LocationUpdater_, hence startLocationUpdates and stopLocationUpdates methods (which,internally, wrap Locator ones)

And about Locator:

<pre>
public interface Locator extends LocationProvider,LocationUpdater,LocationObservable{}
</pre>

We have a base implementation also for _NetworkAwareSearcher_, that is __AbstractNetworkAwareSearcher__:
it simply retrieves connection status - if we are(n't) connected to wifi/umts/other

##The Tasks##
TODO
