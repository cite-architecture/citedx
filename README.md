# citedx · CITE Data Exchange Repository

A repository for openly licensed libraries of data in valid `.cex` format. Licensing information is included in each `.cex` file;  additional details are provided in [libraries/LICENSE.markdown](libraries/LICENSE.markdown).

## The CITE Exchange format

Specifications of the CITE Exchange format are in the `docs` directory.
The latest release version of the specification is version **3.0.2**, available [here](https://cite-architecture.github.io/citedx/CEX-spec-3.0.2/).  Versions 3.0.1 and 3.0.2 correct minro typos;  the requirements of the specification are unchanged from 3.0.

We are currently reviewing the `imagedata` block, and expect to deprecate it soon, in favor of a simpler solution using `datamodels` with CITE collections.


## Freely reusable data sets

All files in the `libraries` subdirectories have passed an automated validation. (See
the following section on how to validate your own files).

To pass validation, the CEX must be syntactically correct.  That is, it must use only valid CEX block labels, and must include a `#!citelibrary` block.

In addition, for libraries that include a text repository, the referential integrity of the repository is validated as follows:

- all URNs identifying  citable texts in the repository's catalog must be unique
- all URNs identifying citable passages in the repository's data block must be unique
- there must be a 1<->1 correspondence between works cataloged as being online and works appearing in the citable data block


## Validating and contributing your own libraries

The `validator` directory includes a scala build file and short script to validate all files in a given directory with file names ending in `.cex`.  You must have scala and sbt installed to run it as follows:

1.  within the `validator` directory, start an sbt console session: `sbt console`
2.  load and run the script:  `:load validate.sc`.  This defines a function named `validate`.
3.  call the function with a directory name (e.g., `validate("mylibrary")`) or omit a directory name to validate the repository's `libraries`directory by  default:  `validate()`.


We encourage you to submit any valid libraries by means of pull-requests to this GitHub repository.
