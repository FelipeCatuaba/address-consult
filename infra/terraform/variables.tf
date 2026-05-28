variable "aws_region" {
  description = "AWS region where the infrastructure will be created."
  type        = string
  default     = "us-east-1"
}

variable "project_name" {
  description = "Project prefix for resource names and tags."
  type        = string
  default     = "address-consult"
}

variable "instance_type" {
  description = "EC2 instance type."
  type        = string
  default     = "t3.micro"
}

variable "key_name" {
  description = "Name of an existing EC2 key pair for SSH access."
  type        = string
}

variable "allowed_ssh_cidr" {
  description = "CIDR allowed to access EC2 via SSH."
  type        = string
  default     = "0.0.0.0/0"
}
