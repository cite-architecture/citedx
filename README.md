# citedx · CITE Data Exchange Repository

A repository for openly licensed libraries of data in valid `.cex` format. Licensing information is included in each `.cex` file;  additional details are provided in [libraries/LICENSE.markdown](libraries/LICENSE.markdown).

The **Sandbox** branch of this repository contains experimental `.cex` files, used for testing libraries, services, and applications. **These are not scholarly data, even if they are “valid” `.cex` files~**

## The CITE Exchange format

Specifications of the CITE Exchange format are available in the [docs directory](docs).

The latest release version of the specification is [version **1.1**](docs/CEX-spec-1.1.md).

The current draft version of the specification is [version **1.2**](docs/CEX-spec-1.2.md).

## Freely reusable data sets

All files in the `libraries` directory have passed an automated validation. (See
the following section on how to validate your own files).

To pass validation, the CEX must be syntactically correct.  That is, it must use only valid CEX block labels, and must include a `#!citelibrary` block.

In addition, for libraries that include a text repository, the referential integrity of the repository is validated as follows:

- all URNs identifying  citable texts in the repository's catalog must be unique
- all URNs identifying citable passages in the repository's data block must be unique
- there must be a 1<->1 correspondence between works cataloged as being online and works appearing in the citable data block


## Validating and contributing your own libraries

The `validator` directory includes a scala build file and short script to validate all files in the `libraries` directory with file names ending in `.cex`.  You must have scala and sbt installed to run it as follows:

1. within the `validator` directory, start an sbt console session: `sbt console`
2. load and run the script:  `:load validate.sc`

You can test your own `.cex` files by dropping them in the libraries directory and running the script.  We encourage you to submit any valid libraries by means of pull-requests to this GitHub repository.
