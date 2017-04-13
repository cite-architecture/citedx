# The CITE Exchange (CEX) format


## Versions

This document specifies version **1.0** of the CITE Exchange format.  Version numbers follow [semantic versioning guidelines](http://semver.org/).

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
- `version`: a version identifier
- `license`: a licensing statement

**Example**:  The following example is a valid `citelibrary` block.  It includes empty lines and a comment lines, in addition to the three required key-value pairs.

    #!citelibrary

    # Metadata applying to the entire library.
    # Note that throughout this source, the string "#" is used as
    # the column delimiter.

    name#Iliadic Metrical Summaries
    version#2017.1
    license#Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.

### `ctsdata`

### `ctscatalog`


### `citedata`

TBA

### `citecatalog`

TBA

### `imagedata`

TBA

### `relations`

TBA

### `orca`

TBA
