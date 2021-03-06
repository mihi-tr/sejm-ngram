package org.sejmngram.sejmometr.json.datamodel.wystapienie;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.sejmngram.sejmometr.json.SejmometrJsonContentDeserializer;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "data", "layers" })
public class Content {

	@JsonProperty("data")
	private WystapienieData data;
	@JsonProperty("layers")
	private Layers layers;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("data")
	public WystapienieData getData() {
		return data;
	}

	@JsonProperty("data")
	@JsonDeserialize(using = SejmometrJsonContentDeserializer.class)
	public void setData(WystapienieData data) {
		this.data = data;
	}

	@JsonProperty("layers")
	public Layers getLayers() {
		return layers;
	}

	@JsonProperty("layers")
	public void setLayers(Layers layers) {
		this.layers = layers;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
