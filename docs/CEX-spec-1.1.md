# The CITE Exchange (CEX) format


## Versions

This document specifies version **1.1** of the CITE Exchange format.  Version numbers follow [semantic versioning guidelines](http://semver.org/).

## What it is

The CITE Exchange format is a plain-text, line-oriented data format for serializing citable content following the models of the CITE Architecture.  Distinct types of content are grouped in separate labelled blocks, as specified below, so that a single CEX source can integrate *any* content citable in the CITE Architecture.

All blocks are optional (although the `citedata` block may only be used in conjunction with a `citecatalog` block:  see details below). Authors  may  limit a CEX serialization to include only those kinds of citable content they choose.  A null string or empty text file is a syntactically valid, although empty, CEX data serialization.

## Syntax of the CEX source: blocks and block labels

1. **Blocks** in a CEX data source are introduced by a line beginning with one of  nine **block labels** listed below.
2. Blocks terminate when a new block is introduced or the end of the data source is reached.
2. Content preceding the first labelled block is ignored.
3. Blocks may occur in any sequence in a single CEX serialization.



Valid block labels are:


- `#!cexversion`
- `#!citelibrary`
- `#!ctsdata`
- `#!ctscatalog`
- `#!citedata`
- `#!citecatalog`
- `#!imagedata`
- `#!relations`
- `#!orca`


## Syntax of the CEX source: block contents

Within a block, the block label is followed by an ordered sequence of lines.  That is, while the appearance of blocks in a CEX source is not ordered, line are ordered within each block.

Empty (zero-length) lines are allowed but are ignored.  Lines beginning with the character `#` are comments and are ignored.  Other lines are treated as the **block contents**.

The syntax of block contents is specific to the type of the block.

## Syntax of individual types of block


### `cexversion`

The `cexversion` block contains a single content line with a string identifying the version of the CITE Exchange format followed  in this CEX source.

**Example**:  The following example is a valid `cexversion` block, using `#` for its delimiting string.  It includes an empty line and a comment line, but only one content line, specifying the version of the CEX data format.

    #!cexversion

    # note: currently using version 1.1
    1.1

### `citelibrary`

The `citelibrary` block contains three content lines with metadata about the entire library of material serialized in this CEX file.  The three items are formatted as key-value pairs.  Each key and value is separated by a string delimiter that does not otherwise appear in content lines of the block.  The required strings for keys and their meaning are;

- `name`: a human-readable name or label for this data set
- `urn`: a CITE2 URN uniquely identifying this library
- `license`: a licensing statement describing rights to use the entire library, as a unit.  Individual components may have more permissive licenses.

**Example**:  The following example is a valid `citelibrary` block, using `#` for its delimiting string.  It includes empty lines and comment lines, in addition to the three required key-value pairs.

    #!citelibrary

    # Metadata applying to the entire library.
    # Note that throughout this source, the string "#" is used as
    # the column delimiter.

    name#Iliadic Metrical Summaries
    urn#urn:cite2:hmt:cex.2017_1:metsumm
    license#Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.


### `ctscatalog`


The `ctscatalog` block contains a table with minimal cataloging data about one or more citable texts.  The table is represented as seven columns of delimited text, with columns separated by a string delimiter that does not otherwise appear in content lines of the block.  The first content line is a header line with labels for each column.  Subsequent content lines document citable versions or exemplars of a text.  The seven columns may have any String labels in the header row, but in all rows columns must follow this sequence:

1. CTS URN for the version or exemplar
2. labels for each tier of the citation hierarchy, with levels separated by a "/" character
3. name of the text group
4. title of the work
5. label of the edition or translation
6. label of the specific exemplar, if any
7. whether the work is online: `true` or `false`


Note that it is possible to catalog texts that are not online.  Within a CEX serialization, cataloging a work as online means that citable texts nodes for this CTS URN must be available in the `ctsdata` block of the CEX.

**Example**:  The following example is a valid `ctscatalog` block, using `#` for its delimiting string.  It defines a version of a text:  note that the column for exemplar label is empty.

    #!ctscatalog

    # Complete catalog for a single citable text: an edition
    # of scholia in a manuscript of the *Iliad*.  There is no
    # specific exemplar of this edition.

    urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
    urn:cts:greekLit:tlg5026.msA.hmt:#book/comment/section#Scholia Vetera in Iliadem#Main scholia to Venetus A#Homer Multitext##true


### `ctsdata`


The `ctsdata` block contains a two-column representation of a citable text in the OHCO2 model.  Columns are separated by a string delimiter that does not otherwise appear in content lines of the block.  The first column gives the CTS URN for a citable node; the second column gives it text contents.  Within a given citable version or exemplar, nodes must be in document order.

**Example**: The following example is a valid `ctscatalog` block, using `#` for its delimiting string.  It defines two citable nodes of text.

    #!ctscatalog
    # CTS data:  "#" is the column delimiter.

    urn:cts:greekLit:tlg5026.msA.hmt:1.2.lemma#μῆνις
    urn:cts:greekLit:tlg5026.msA.hmt:1.2.comment#παρὰ τὸ μένω μῆνις ὡς ἐνὸς ἦνις· οἱ δὲ περὶ Γλαύκωνα τὸν Ταρσέα ἠξίουν ὀξύνειν τὸ ὄνομα οὐκ ὀρθῶς.



### `citecatalog`

The `citecatalog` contains delimited text documenting the structure of one or more citable collections of data.  Within this block, statements are of one of two kinds, identified by the first column:  `collection` lines document a single collection;  `property` lines document a specific property in a collection represented by a `collection` line.

In `collection` lines, the initial keyword is followed by four further columns of delimited text data.  These give, in order:

1. The URN of the collection
2. A label for the collection
3. The URN of the property definining labels for each object in the collection.
4. A rights statement applying to the contents of the collection.

In `property` lines, the initial keyword is followed by four further columns of delimited text data.  These give, in order:

1. The URN of the property
2. A label for the property
3. The data type of the property.  Valid values for data type are `String`,`CtsUrn`,`Cite2Urn`,`Number` and `Boolean`.
4. An optional list of controlled vocabulary items for a `String` type.  These are separated by a secondary delimiter that does not occur elsewhere in the value of this property.

Every collection must have one property with property identifier `urn`;  its type must be `Cite2Urn`.



**Example**: The following example is a valid `citecatalog` block, using `#` for its delimiting string, and `,` for its secondary delimiter for controlled vocabulary lists.  It defines a collection with three properties.  In addition to the required `urn` property, there are two properties of `String` type.  The string property labelled "License for binary image data" has a controlled vocabulary list with two comma-delimited items.



    #!citecatalog

    collection#urn:cite2:hmt:vaimg.v1:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.v1.caption:##CC-attribution-share-alike

    property#urn:cite2:hmt:msA.v1.urn:#Image URN#Cite2Urn#
    property#urn:cite2:hmt:msA.v1.caption:#Caption#String#
    property#urn:cite2:hmt:msA.v1.rights:#License for binary image data#String#CC-attribution-share-alike,public domain


### `citedata`

The `citedata` block contains delimited text records for objects in a single CITE Collection.  A CEX source documenting multiple CITE Collections will therefore have one `citedata` block for each collection.  The collection represented by the `citedata` block must be documented by content in a `citecatalog` block defining its structure.  (On the other hand, the `citecatalog` block may define the structure of collections with no data in a `citedata` block.)

The first content line of the `citedata` block is a header line identifying the property defined in the `citecatalog` block that is represented in this column.  The property identifier in the header line must match the property identifier of the catalog's property URN, in a case-insensitivie match.  Any of the headers `URN`, `Urn` or `urn` will match the required `urn` property of the collection, for example.

Subsequent content lines give data values for a single object.  Data values in each delimited column must be correct string serializations of that data type, as follows:

- `String` data type:  The value may be any string not containing the delimiting string used in this CEX source, unless the property is defined with a controlled vocabulary list.  In that case, the value must be one of the string values defined in the catalog.
- `CtsUrn` data type: The value must be a valid string representation of a Cts URN.
- `Cite2Urn` data type: The value must be a valid string representation of a CITE2 URN.  (See this [introduction to the CITE2 URN type](http://cite-architecture.github.io/2017/02/02/cite2urn_update/).)
- `Boolean` data type:  The value must be one of the strings `true` or `false`.
- `Number` data type: For integers, the value must be a string of digit characters representing a base-ten integer.  For non-integral values, the text representation must begin with a string of digit characters representing a base-ten integer  or `0`, followed by a decimal point `.`, followed by a string of digit characters representing decimal digits.


 Values for the required `urn` property must be unique within a collection.

**Example**:   The following example is a valid `citedata` block, using `#` for its delimiting string.  It defines a single object with three properties.  The values in each column are valid for the property definitions in the example `citecatalog` block above.


    #!citedata
    # Images of the Venetus A manuscript:

    URN#Caption#Rights
    urn:cite2:hmt:vaimg.v1:IMG1#Folio 1r of the Venetus A# photographed in natural light#CC-attribution-share-alike


### `imagedata`

TBA

### `relations`

TBA

### `orca`

TBA
