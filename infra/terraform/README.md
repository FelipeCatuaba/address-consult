# Terraform EC2 (Simple)

Infra minimalista para subir uma EC2 com Docker pronto para rodar a API.

## O que cria
- 1 EC2 Amazon Linux 2023
- 1 Security Group
  - `22` (SSH) a partir do CIDR configurado
  - `8080` (API) aberto
- Uso da VPC/subnet default da conta

## Antes de aplicar
1. Tenha um key pair criado na AWS (EC2 Key Pairs).
2. Configure credenciais AWS no seu ambiente.
3. Copie `terraform.tfvars.example` para `terraform.tfvars` e ajuste os valores.

## Comandos
```bash
cd infra/terraform
terraform init
terraform plan
terraform apply
```

## Outputs importantes
- `ec2_public_ip`
- `api_base_url`

## Limpeza
```bash
terraform destroy
```
