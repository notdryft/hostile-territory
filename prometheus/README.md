# Running Prometheus and Node Exporter

Get latest release of [Prometheus](https://github.com/prometheus/prometheus/releases) and [Node Exporter](https://github.com/prometheus/node_exporter/releases).

Some collectors needs to be enabled explicitly when running Node Exporter:

```
./node_exporter \
  --collector.interrupts \
  --collector.tcpstat \
  --collector.vmstat
```

Inside prometheus directory, add the following to `prometheus.yml` after `scrape_configs`:

```yaml
scrape_configs:
  # keep...
  - job_name: 'node'
    static_configs:
      - targets: ['localhost:9100']
```

Running prometheus:

```
./prometheus
```

Then, you'll need to:

* Go to the [grafana directory](https://github.com/notdryft/hostile-territory/tree/master/grafana).
* Configure a Prometheus Datasource pointing to your instance's Prometheus
* Import `dashboard.json`