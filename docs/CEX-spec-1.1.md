# The CITE Exchange (CEX) format


## Versions

This document specifies version **1.1** of the CITE Exchange format.  Version numbers follow [semantic versioning guidelines](http://semver.org/).

## What it is

The CITE Exchange format is a plain-text, line-oriented data format for serializing citable content following the models of the CITE Architecture.  Distinct types of content are grouped in separate labelled blocks, as specified below, so that a single CEX source can integrate *any* content citable in the CITE Architecture.

All blocks are optional. Authors  may  limit a CEX serialization to include only those kinds of citable content they choose.  A null string or empty text file is a syntactically valid, although empty, CEX data serialization.

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

**Example**:  The following example is a valid `cexversion` block.  It includes an empty line and a comment line, but only one content line, specifying the version of the CEX data format.

    #!cexversion

    # note: starting out with version 1.0
    1.0

### `citelibrary`

The `citelibrary` block contains three content lines with metadata about the entire library of material serialized in this CEX file.  The three items are formatted as key-value pairs.  Each key and value is separated by a string delimiter that does not otherwise appear in content lines of the block.  The required strings for keys and their meaning are;

- `name`: a human-readable name or label for this data set
- `urn`: a CITE2 URN uniquely identifying this library
- `license`: a licensing statement describing rights to use the entire library, as a unit.  Individual components may have more permissive licenses.

**Example**:  The following example is a valid `citelibrary` block.  It includes empty lines and a comment lines, in addition to the three required key-value pairs.

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

**Example**:

    #!ctscatalog

    # Complete catalog for a single citable text: an edition
    # of scholia in a manuscript of the *Iliad*.  There is no
    # specific exemplar of this edition.

    urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
    urn:cts:greekLit:tlg5026.msA.hmt:#book/comment/section#Scholia Vetera in Iliadem#Main scholia to Venetus A#Homer Multitext##true


### `ctsdata`


The `ctsdata` block contains a two-column representation of a citable text in the OHCO2 model.  Columns are separated by a string delimiter that does not otherwise appear in content lines of the block.  The first column gives the CTS URN for a citable node; the second column gives it text contents.  Within a given citable version or exemplar, nodes must be in document order.

**Example**:

    # Valid CTS data.  "#" is the column delimiter

    urn:cts:greekLit:tlg5026.msA.hmt:1.2.lemma#μῆνις
    urn:cts:greekLit:tlg5026.msA.hmt:1.2.comment#παρὰ τὸ μένω μῆνις ὡς ἐνὸς ἦνις· οἱ δὲ περὶ Γλαύκωνα τὸν Ταρσέα ἠξίουν ὀξύνειν τὸ ὄνομα οὐκ ὀρθῶς.



### `citecatalog`

The `citecatalog` contains delimited text documenting the structure of citable collections of data.  Within this block, statements are of one of two kinds, identified by the first column:  `collection` lines document a single collection;  `property` lines document.

The keyword `collection` is followed by four further columns of delimited text data.  These give, in order:

1. The URN of the collection
2. A label for the collection
3. The URN of the property definining labels for each object in the collection.
4. A rights statement applying to the contents of the collection.

The keyword `property` is followed fourt further columns of delimited text data.  These give, in order:

1. The URN of the property
2. A label for the property
3. The data type of the property.  Valid values for data type are `String`,`CtsUrn`,`Cite2Urn`,`Number` and `Boolean`.
4. An optional list of controlled vocabulary items for a `String` type.  These are separated by a secondary delimiter that does not occur elsewhere in the value of this property.

Every collection must have one property with property identifier `urn`;  its type must be `Cite2Urn`; and all values given for the `urn` property in the `citedata` block must be unique within that collection.



**Example**: This example defines a collection with three properties. The delimiting string is `#`; the secondary delimiter is `,`.   In addition to the required `urn` property, there are two properties of `String` type.  The string property labelled "License for binary image data" has a controlled vocabulary list with two comma-delimited items.


        #!citecatalog
        collection#urn:cite2:hmt:vaimg.v1:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.v1.caption:##CC-attribution-share-alike

        property#urn:cite2:hmt:msA.v1.urn:#Image URN#Cite2Urn#
        property#urn:cite2:hmt:msA.v1.caption:#Caption#String#
        property#urn:cite2:hmt:msA.v1.rights:#License for binary image data#String#CC-attribution-share-alike,public domain


### `citedata`

MUST BE ACCOMPANIED BY catalog block.

**Example**:


        #!citedata
        URN,Caption,Rights
        urn:cite2:hmt:vaimg.v1:IMG1#Folio 1r of the Venetus A, photographed in natural light#CC-attribution-share-alike


### `imagedata`

TBA

### `relations`

TBA

### `orca`

TBA
