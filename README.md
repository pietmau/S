## PLEASE NOTE
- To run the application please select the **prod** flavour;
- to run the UI tests please select the **mock** flavour.

### Features
- Kotlin, RxJava, Dagger 2;
- Kotlin Android Extensions;
- Architecture Components ViewModel to retain the cached data across config changes;
- MVP;
- Espresso, Mockito;
- DiffUtil;
- a different layout is used in Landscape.

### Caching
Caching is implemented in the `CachedRetrofitModel`.
Other approaches might include using OKHttp caching or implementing manually a memory/file cache.
RxJava `Observable.replay(window,TimeUnit)` replays the events in the time window, but outside of
it becomes empty.


### TODO()

