package com.lincompute.multiplication;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;


public class PerformanceMetrics 
{

	public PerformanceMetrics() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		 // Get the OS bean to monitor CPU usage
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        // Record the start time
        long startTime = System.nanoTime();
        
        // Record initial CPU time
        long initialCpuTime = osBean.getProcessCpuTime();
        
        // Example computation (Replace this with the actual program you want to measure)
        long sum = 0;
        for (long i = 0; i < 1_000_000_000L; i++) {
            sum += i;
        }

        // Record the end time
        long endTime = System.nanoTime();
        
        // Record final CPU time
        long finalCpuTime = osBean.getProcessCpuTime();

        // Compute execution time in milliseconds
        long executionTime = (endTime - startTime) / 1_000_000;
        
        // Compute CPU consumption in percent
        long cpuTimeUsed = finalCpuTime - initialCpuTime;
        double cpuUsage = (cpuTimeUsed / (double) (endTime - startTime)) * 100;
        
        // Print results
        System.out.println("Execution Time: " + executionTime + " ms");
        System.out.println("CPU Usage: " + String.format("%.2f", cpuUsage) + " %");

	}

}
