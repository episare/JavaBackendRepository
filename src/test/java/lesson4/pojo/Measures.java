package lesson4.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "original",
        "metric",
        "us"
})

@Data
public class Measures {

    @JsonProperty("original")
    private UnitMeasurement original;
    @JsonProperty("metric")
    private UnitMeasurement metric;
    @JsonProperty("us")
    private UnitMeasurement us;

}