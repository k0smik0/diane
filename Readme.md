# Diane
Diane is an Android framework for (location) aware searching.
  
What about "_aware_"?  

  - imagine you want search some data from a remote provider, and these data are geolocated;  
  - imagine also you want implement once time your business logic for search data from a remote provider, choosing between different providers using ttl or similar parameters;  
  - imagine, finally, that your remote provider is not working, and you have to backup to local provider (a king of cache, isn't?)
  - of course, you want use a fresh location for your search

So, you would a component make this "dirty work" for you, hiding some checks before start the search; and checks can be, for example:

  - "location usefulness": last search used a geolocation not so far from fresh location, retrieved just now: so, is a new search really useful?
  - "network awareness": connection is not available, or remote server doesn't give a reasonable response. Do you would handle this happening? How? a simple message or using internal cache?
  - "cache awareness": and local cache is consistent? perhaps it could be too old, and its data could not be so useful - so you would handle also this accident
  - other additional checks


Diane is a component implementing all these strategies: it provides some default behaviour (as location "usefulness" check), and lets you free to implement your custom business logic.

It is generics based, so result agnostic. You have to specify your data type at instance time or extending base/abstract class.

## How-To

#### The oop contract
"Searcher" is hierarchy founder
<pre>
<code>
public interface Searcher<SearchParams,SearchState,SearchResult> {
  public SearchState search(SearchParams... params) throws SearchException;
  public SearchResult getResult();
}
</code>  
</pre>
and all is a Searcher specialization.

#### The implementation

For a fast using, there is DefaultFullAwareSearcher: it hides some of above logic, directly (as "is a search still working") or delegating to injected component (geolocation usefulness, network/cache awareness, etc)


<code>
public class DefaultFullAwareSearcher<SearchResult> implements	FullAwareSearcher<SearchResult>
</code>

just instance this, et voilà (of course you have specify generics parameters).

This class need two constructor parameters:

 - ThreeStateLocationAwareLocationSupplier - this is the "location aware" responsible
 - LocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> - it is the effective search actor, and it hides network/cache awareness logics

These above are interfaces, but some concrete/abstract classes are provided for instancing, that is: DefaultThreeStateLocationAwareLocationSupplier and DefaultLocalizedSearcherCacheNetworkAwareStrictChecking.

These above are interfaces, but some concrete/abstract classes are provided for instancing, that is: 

 - DefaultThreeStateLocationAwareLocationSupplier  
 - DefaultLocalizedSearcherCacheNetworkAwareStrictChecking.

Again, you need provide arguments for these two above:

  - DefaultThreeStateLocationAwareLocationSupplier needs for LocationProvider (it is an interface from Polaris )
  - DefaultLocalizedSearcherCacheNetworkAwareStrictChecking needs for:
    - a LocalizedSearcherCacheAware 
    - a LocalizedSearcherNetworkAware

There are still interfaces, and abstract classes are provided: you have to implement "doSearch" methods with your business search logic (for remote search or cache search), et voilà.

#### The UML
Check the uml class diagram for better focus about hierarchy:
[Diane](library/common/uml/Diane.png)

#### Examples

Examples are more simpler to understand: check for [vanilla](sample/vanilla) or [roboguiced](sample/roboguiced) version.  

Vanilla version is provided just for test or "education" purposes, while I use, really, roboguiced version.   
Its dependency injection handle allows to to forget all about passing arguments to  classes constructors: let IoC container does for you.  
In this way you have just to implement "doSearch" methods and binding the interfaces to concrete class in roboguice module class (5 rows code, check out examples).
  
This [video](http://www.youtube.com/watch?v=pA4CGULfZpU) show some demos.
  
--
  
  The directory "test_sample-utils" are some mock components, useful for test location-awareness Diane: something as "MockLocationProvider" to retrieve geolocation, and something as "MockLocationsInjector", to provide fake locations to device (real or simulator).
  
See this [video](http://www.youtube.com/watch?v=Q3tfgJBrnwk) for a mock locations tool demo.
