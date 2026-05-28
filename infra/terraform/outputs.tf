output "ec2_instance_id" {
  description = "EC2 instance id."
  value       = aws_instance.app.id
}

output "ec2_public_ip" {
  description = "Public IP to access the API and SSH into the instance."
  value       = aws_instance.app.public_ip
}

output "api_base_url" {
  description = "Base URL to call the API."
  value       = "http://${aws_instance.app.public_ip}:8080"
}
