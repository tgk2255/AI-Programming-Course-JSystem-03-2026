-- liquibase formatted sql

-- changeset codex:002-chat-role-and-customer-profile
ALTER TABLE public.chat_message
    ADD COLUMN IF NOT EXISTS role VARCHAR(20) NOT NULL DEFAULT 'user';

CREATE TABLE IF NOT EXISTS public.customer_profile (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    pesel VARCHAR(40) NOT NULL,
    monthly_income NUMERIC(12, 2) NOT NULL,
    currency VARCHAR(8) NOT NULL DEFAULT 'PLN',
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX IF NOT EXISTS uq_customer_profile_pesel
    ON public.customer_profile (pesel);

INSERT INTO public.customer_profile (first_name, last_name, pesel, monthly_income, currency)
VALUES ('Janek', 'Tester', '1234567', 8500.00, 'PLN')
ON CONFLICT (pesel) DO NOTHING;
