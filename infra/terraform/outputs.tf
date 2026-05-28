output "ec2_instance_id" {
  description = "EC2 instance id."
  value       = aws_instance.app.id
}

output "vpc_id" {
  description = "VPC id in use."
  value       = data.aws_vpc.default.id
}

output "subnet_id" {
  description = "Subnet id used by the EC2 instance."
  value       = aws_instance.app.subnet_id
}

output "security_group_id" {
  description = "Security group attached to EC2."
  value       = aws_security_group.app_sg.id
}

output "ec2_public_ip" {
  description = "Public IP to access the API and SSH into the instance."
  value       = aws_instance.app.public_ip
}

output "api_base_url" {
  description = "Base URL to call the API."
  value       = "http://${aws_instance.app.public_ip}:8080"
}
