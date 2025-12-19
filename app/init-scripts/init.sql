-- Create receipt table first since it's referenced by expense
CREATE TABLE receipt (
    id SERIAL,
    receipt_image TEXT,
    ocr_extracted_text TEXT,
    merchant_name TEXT,
    merchant_address TEXT,
    scan_date TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT pk_receipt PRIMARY KEY (id)
);

-- Create expense table
CREATE TABLE expense (
    id SERIAL,
    amount NUMERIC,
    description TEXT,
    location TEXT,
    merchant TEXT,
    payment_method TEXT,
    deleted BOOLEAN NOT NULL DEFAULT false,
    recurring BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    receipt_id BIGINT UNIQUE,
    CONSTRAINT pk_expense PRIMARY KEY (id),
    CONSTRAINT fk_expense_receipt FOREIGN KEY (receipt_id) REFERENCES receipt(id)
);

-- Create category table
CREATE TABLE category (
    id SERIAL,
    name TEXT,
    description TEXT,
    deleted BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    expense_id BIGINT,
    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT fk_category_expense FOREIGN KEY (expense_id) REFERENCES expense(id)
);

-- Create indexes for better performance
CREATE INDEX idx_expense_receipt_id ON expense(receipt_id);
CREATE INDEX idx_category_expense_id ON category(expense_id);
CREATE INDEX idx_expense_created_at ON expense(created_at);
CREATE INDEX idx_category_created_at ON category(created_at);
CREATE INDEX idx_receipt_created_at ON receipt(created_at);