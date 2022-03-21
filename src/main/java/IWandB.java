package py4j.wandb;
import java.util.List; 
import java.util.Map;

public interface IWandB {
    // The most commonly used functions/objects are:
    // - wandb.login -> login 
    // - wandb.init — initialize a new run at the top of your training script  (in wandb.sdk.wandb_init.py line 742)
    // - wandb.config — track hyperparameters and metadata
    // - wandb.log — log metrics and media over time within your training loop
        
    public Boolean login(
        String anonymous,
        String key,
        Boolean relogin,
        String host,
        Boolean force,
        Integer timeout
    );

    public Boolean log(Map<String, Object> data, Integer step, Boolean commit, Boolean sync);
    public Boolean logArtifact(String artifactPath, String name, String type, List<String> aliases);
    public Boolean init(String job_type, String dir, Map<String, Object> config,
                        String project, String entity, Boolean reinit, List<String> tags, 
                        String group, String name, String notes, 
                        Map<String, Object> magic, List<String> configExcludeKeys, List<String> configIncludeKeys,
                        String anonymous, String mode, Boolean allowValChange, 
                        Boolean resume, Boolean forece, Object tensorboard, Object syncTensorboard, Object monitorGym, Object saveCode, 
                        String id, Map<String, Object> settings);
    public Boolean finish();
    



}