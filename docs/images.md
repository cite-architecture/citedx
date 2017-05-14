## CITE Image extension

### A regular CITE Collection

Like all extensions to CITE Collections, CITE Image collections are, in the first place, regular CITE Collections.   That means all data represented by structured text appears in normal `citecatalog` and
`citedata` blocks.

**Example**:  The `citecatalog` block defines a a collection with three properties.  The `citedata` section defines one object in that collection.

    #!citecatalog
    collection#urn:cite2:hmt:vaimg.v1:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.v1.caption:##CC-attribution-share-alike

    property#urn:cite2:hmt:msA.v1.urn:#Image URN#Cite2Urn#
    property#urn:cite2:hmt:msA.v1.caption:#Caption#String#
    property#urn:cite2:hmt:msA.v1.rights:#License for binary image data#String#CC-attribution-share-alike,public domain

    #!citedata
    URN,Caption,Rights
    urn:cite2:hmt:vaimg.v1:IMG1#Folio 1r of the Venetus A, photographed in natural light#CC-attribution-share-alike


### Extended information

The CITE Image extension extends this collection in two ways to support working with binary image data, as well as the structured text data directly included in the CEX representation.

First it requires that one property of the Collection identify licensing for each specific image.  CITE Colletions have a required rights property applying to the entire collection *qua* collection:  this additional licensing statement specifically applies to binary image data for an individual image.  Thus, the structured text in the CITE Collection is covered by the collection's rights statement, and might identify individual images in the collection licensing binary image data under different terms.  (E.g., in the example catalog above, the rights string is a controlled vocabulary type:  some images in your collection might be public domain;  others might be CC-attribution-share-alike.)

Second, it identifies protocols for getting access to binary image data for the images documented in the collection.

These two requirements can be addressed in a simple four-column entry, illustrated here:


    #!imagedata
    collection,protocol,baseUrl,rights
    urn:cite2:hmt:vaimg.v1:,CITE image,http://www.homermultitext.org/hmtdigital/images?,urn:cite2:hmt:msA.v1.rights:
    urn:cite2:hmt:vaimg.v1:,IIIF,http://www.homermultitext.org/image2/context.json,urn:cite2:hmt:msA.v1.rights:

The first column identifies the collection extended for images by this definition.

The second column identifies a protocol.  Note that the same collection might be extended by more than one image protocol.  This allows the CITE image extension an easy mechanism for incorporating future binary image manipulation protocols.  In this example, the image collection is extended by both the CITE image REST API, and the IIIF protocol.

The third  column is a base URL of a service where this binary image data for this collection is available under the given protocol.  The meaning of this base URL will be determined by the specific protocol.  (Again, this allows open-ended expansion in the future.)  For the CITE image protocol, the base URL would likely be the url to which defined REST requests can be appended.  For IIIF it might instead be the context URL for the service, from which client software would extract information about how to form URLs for requests.  (Or maybe not:  this is a mock example for discussion, not a formal proposal for IIIF implementations.)

The fourth column is just the URN of the property containing specific rights statements for the binary image.  It might seem that repeating this value in two protocol definitions violates DRY principles, in fact the repetition is a coincidence:  in this illustration, the same rights statements applies to binary data from either protocol, but this mechanism allows us to extend a CITE collection with binary data served from different protocols potentially under different license terms.
