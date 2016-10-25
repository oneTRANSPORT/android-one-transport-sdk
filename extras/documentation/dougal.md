Dougal: oneM2M for Android SDK
==============================

The server for oneTRANSPORT follows the oneM2M standard for a *Common
Services Entity*, and the Android client becomes an Application Entity that
can request oneTRANSPORT resources.

## Resource structure

Access to the CSE is by RESTful HTTP, so resources are stored hierarchically
and referred to by their URIs.  The CSE has a base URL root, then below that
is the name of the application entity.

For example:

> **Base URL**: `https://cse-01.onetransport.uk.net/ONETCSE01`

> **Application entity name**: `Oxfordshire`

> **Path**: `v2.0/Carpark/All`

So to retrieve a JSON object representing all the car parks in Oxfordshire,
you would make a GET request to this URL:

> **`https://cse-01.onetransport.uk.net/ONETCSE01/Oxfordshire/v2.0/Carpark/All`**

According to oneM2M, `v2.0`, `Carpark` and `All` are containers.  These are
similar to directories in that they can have other containers as children or
resources called content instances.

A content instance contains actual data such as a JSON object or array, and
is wrapped by a oneM2M JSON envelope that has information like the creation
time and name of this resource.

As new content instances are added to a container, they form a stack of
immutable data.  You cannot update a content instance although you can delete
it.  Often we want to get the latest content instance from a container, so
to do that the URI should have `/la` appended to it.

> **`https://cse-01.onetransport.uk.net/ONETCSE01/Oxfordshire/v2.0/Carpark/All/la`**

Dougal, a partial implementation of the oneM2M standard, provides an Android
API to do a lot of this for you.

## Retrieving resources

To retrieve a container, for example, from the CSE you would do this:

        import com.interdigital.android.dougal.resource.Container;
        ...
        Container container = Container.retrieve(AE_ID, BASE_URL, PATH_RETRIEVE, TOKEN, null);

Synchronous calls like this one throw exceptions on error.  The last parameter
could be a `FilterCriteria` object that could apply a filter to resources
before returning the results to the client.  However, it currently can impose
a very large load on our pre-production CSE so we don't normally use it.

The container has some information that you might find useful, such as the
number of content instances it contains.  However, most of the time what you
probably want to do is retrieve the latest content instance inside a
container.  In that case, this is the call:

        ContentInstance contentInstance = Container.retrieveLatest(AE_ID, BASE_URL, PATH_RETRIEVE, TOKEN, null);

It is also possible to retrieve the oldest or first content instance in this
container and its enclosed data:

        ContentInstance contentInstance = Container.retrieveOldest(AE_ID, BASE_URL, PATH_RETRIEVE, TOKEN, null);
	String content = contentInstance.getContent();

## Creating resources

You can create a new container under your application entity on the CSE as
follows:

        Container container = new Container(AE_ID, ID, NAME, BASE_URL, PATH_CREATE);
        container.create(TOKEN);

A top-level container would have `PATH_CREATE = /CSE_NAME/AE_NAME`.

The same pattern is used to create any other resource, like a content
instance:

        ContentInstance contentInstance = new ContentInstance(AE_ID, ID, NAME, BASE_URL, PATH_CREATE, CONTENT);
        contentInstance.create(TOKEN);

## Updating resources

It is not permitted to update a content instance, but to alter a container,
for example, would be done like this:

        Container container = Container.retrieve(AE_ID, BASE_URL, PATH_RETRIEVE, TOKEN, null);
        container.setMaxByteSize(2000000L);
        container.update(TOKEN);

## Deleting resources

A resource can be deleted with a static call or using an instance:

        Container.delete(AE_ID, BASE_URL, PATH_RETRIEVE, TOKEN); // static

        Container container = Container.retrieve(AE_ID, BASE_URL, PATH_RETRIEVE2, TOKEN, null);
        container.delete(TOKEN); // instance

## Asynchronous methods

Dougal supports background processing for create, retrieve, update and delete.
Typically method names end in `Async` and take a `DougalCallback` object which
is used to return the results or an exception.

To retrieve a content instance asynchronously, make the call:

        Container.retrieveLatestAsync(AE_ID, BASE_URL, PATH_RETRIEVE, TOKEN, new Callback());

And in your callback class implement the interface:

        public class Callback implements DougalCallback {

            public void getResponse(Resource resource, Throwable throwable){
                if (throwable != null) {
                    // There was a problem.
                    Log.e(TAG, throwable.getMessage());
                } else if (resource != null) {
                    // Call returned some data, do something.
                }
            }
        }

## Tree traversal

If you don't know the container structure on the CSE, you can find all the
child resources of a particular resource.  Iterating down the tree, you will
be able to find all the resources on the CSE.

For example, to find the resource identifiers of all the application entities
on a CSE:

        ApplicationEntity ae = ApplicationEntity.retrieveChildren(AE_ID, BASE_URL, PATH_RETRIEVE, TOKEN);
        ResourceChild[] children = ae.getResourceChildren();
        for (ResourceChild child : children) {
            Log.i(TAG, "Child: " + child.getName);
        }
