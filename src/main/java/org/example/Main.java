package org.example;

public class Main {
    public static void main(String[] args) {

    }
    /*
  Assume we have 2 clients - DeviceClient and MetricClient.
  DeviceClient may return short info about devices by their ids.
  MetricClient may return all metrics for the list of devices by their ids.
  Both clients are configured as Spring beans that can be autowired to other beans.

  You need to implement a method getDevicesDetails(Set<UUID> ids) in DeviceManager class.
  The method must return the list of devices details.

  !!!IMPORTANT  metrics history in device details must contain
                ONLY items registered after the last device reboot time.

  !!!NOTE  code style will also be taken into account when the solution is evaluated.
*/

//------------------------------------------------------------------------------------------------------
}