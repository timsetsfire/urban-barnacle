import logging
from py4j.clientserver import ClientServer, JavaParameters, PythonParameters
import wandb
import sys


class PyIWandB(object):
    def __init__(self):
        self.active_run = None
        self.config = None

    def login(
        self,
        anonymous=None,
        key=None,
        relogin=None,
        host=None,
        force=None,
        timeout=None,
    ):
        res = wandb.login(anonymous, key, relogin, host, force, timeout)
        return res

    def init(
        self,
        job_type,
        dir,
        config,
        project,
        entity,
        reinit,
        tags,
        group,
        name,
        notes,
        magic,
        configExcludeKeys,
        configIncludeKeys,
        anonymous,
        mode,
        allowValChange,
        resume,
        forece,
        tensorboard,
        syncTensorboard,
        monitorGym,
        saveCode,
        id,
        settings,
    ):
        run = wandb.init(
            job_type,
            dir,
            config,
            project,
            entity,
            reinit,
            tags,
            group,
            name,
            notes,
            magic,
            configExcludeKeys,
            configIncludeKeys,
            anonymous,
            mode,
            allowValChange,
            resume,
            forece,
            tensorboard,
            syncTensorboard,
            monitorGym,
            saveCode,
            id,
            settings,
        )
        self.active_run = run
        return True

    def logArtifact(self, artifact_path, name, type, aliases):
        if self.active_run is None:
            wandb.log_artifact(artifact_path, name, type, aliases)
        else:
            self.active_run.log_artifact(artifact_path, name, type, aliases)
        return True

    def log(self, data, step, commit, sync):
        if self.active_run is None:
            wandb.log(data, step, commit, sync)
        else:
            self.active_run.log(data, step, commit, sync)
        return True

    def finish(self):
        if self.active_run is None:
            wandb.finish()
        else:
            self.active_run.finish()
        return True

    def toString(self):
        return "WandB Gateway"

    class Java:
        implements = ["py4j.wand.IWandB"]


# if __name__ == "__main__":

#     _, java_address, java_port, python_address, python_port = sys.argv
#     java_params = JavaParameters(address = java_address, port = int(java_port))
#     python_params = PythonParameters(address = python_address, port = int(python_port))
#     wandb_gateway = PythonWandBGateway()
#     gateway = ClientServer(
#         java_parameters=JavaParameters(),
#         python_parameters=PythonParameters(),
#         python_server_entry_point=wandb_gateway,
#     )
#     gateway = ClientServer(
#         java_parameters=java_params,
#         python_parameters=python_params,
#         python_server_entry_point=wandb_gateway,
#     )
    # print("wandb_gateway started")


# Make sure that the python code is started first.
# Then execute: java -cp py4j.jar
# py4j.examples.SingleThreadClientApplication

# from py4j.java_gateway import JavaGateway, CallbackServerParameters
# simple_hello = SimpleHello()

# gateway = JavaGateway(
#     callback_server_parameters=CallbackServerParameters(),
#     python_server_entry_point=simple_hello
#     # python_server_entry_point=simple_hello
# )
