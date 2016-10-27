Retriever API
=============

Current feed data from oneTRANSPORT is made available through retriever
objects that wrap HTTPS GET requests to the CSE.  These can be synchronous
or asynchronous, with the async version using an AsyncTaskLoader to manage
the background thread for you.

Retrievers simply query the CSE and get the latest information, with no
caching or access to historical resources.

## Synchronous usage

Retriever objects return arrays of resources inflated from JSON returned by
the CSE.  Suppose we want to acquire the latest state of car parks in
Buckinghamshire, this is the code you need:

        import net.uk.onetransport.android.county.bucks.carparks.CarPark;
        import net.uk.onetransport.android.county.bucks.carparks.CarParkRetriever;
        
        ...
        
        CarPark[] carParks = new CarParkRetriever(getContext()).retrieve();

As well as car parks, there are also events, roadworks, traffic flow, traffic
queues, traffic scoot data, traffic speeds, traffic travel times and variable
message signs.  See [all available data](#docs/android/available_data.md).

## Background loading

If your app does not already have a background thread on which to make the
retriever call, then one will have to be created specially.  The loader
pattern is used in this example, fetching the roadworks currently under way
in Northamptonshire:

            import net.uk.onetransport.android.county.northants.generic.RetrieverResult;
            import net.uk.onetransport.android.county.northants.roadworks.Roadworks;
            import net.uk.onetransport.android.county.northants.roadworks.RoadworksRetrieverLoader;
            ...
            
            public class NorthantsRoadworksFragment extends Fragment
                         implements LoaderManager.LoaderCallbacks<RetrieverResult<Roadworks>> {
        
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                ...
                getLoaderManager().initLoader(LOADER_ID, null, this);
                ...
            }
        
            @Override
            public Loader<RetrieverResult<Roadworks>> onCreateLoader(int id, Bundle args) {
                return new RoadworksRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
            }
            
            @Override
            public void onLoadFinished(Loader<RetrieverResult<Roadworks>> loader,
                                       RetrieverResult<Roadworks> data) {
                if (data.getExceptions().size() > 0 || data.getContent() == null) {
                    throw new Throwable("NORTHANTS road works array error"));
                } else {
                   Roadworks[] roadworks = data.getContent();
                   ...
                }
            }
        
            @Override
            public void onLoaderReset(Loader<RetrieverResult<Roadworks>> loader) {
                // Nothing needs to be done.
            }
        }
