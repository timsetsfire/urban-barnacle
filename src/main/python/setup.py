from setuptools import setup, find_packages
import os

# The directory containing this file
root = os.path.dirname(os.path.abspath(__file__))

with open(os.path.join(root, "requirements.txt")) as f:
    requirements = f.read().splitlines()

setup(
    name="wandb_gateway",
    version="0.1.0",
    description="simple gateway to expose some wandb functionality to jvm languages",
    scripts=["bin/start_wandb_gateway"],
    install_requires=requirements,
    zip_safe=False,
    include_package_data=True,
    packages=find_packages("."),
    python_requires=">=3.4,<3.9",
)