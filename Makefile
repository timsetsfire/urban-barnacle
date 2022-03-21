.PHONY: wandb-scala

dist: scala_components python_components

scala_components:
	sbt assembly

python_components:
	cd src/main/python && $(MAKE) && pip install dist/wandb_gateway*.whl -U

