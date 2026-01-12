-- Create receipt table
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

-- Create category table
CREATE TABLE category (
      id SERIAL,
      name TEXT,
      description TEXT,
      deleted BOOLEAN NOT NULL DEFAULT false,
      created_at TIMESTAMP,
      updated_at TIMESTAMP,
      CONSTRAINT pk_category PRIMARY KEY (id)
);

-- Create expense table
CREATE TABLE expense (
    id SERIAL,
    receipt_id BIGINT UNIQUE,
    category_id BIGINT null,
    amount NUMERIC not null,
    description TEXT,
    location TEXT,
    payment_method TEXT,
    deleted BOOLEAN NOT NULL DEFAULT false,
    recurring BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT pk_expense PRIMARY KEY (id),
    CONSTRAINT fk_expense_receipt FOREIGN KEY (receipt_id) REFERENCES receipt(id),
    CONSTRAINT fk_expense_category FOREIGN KEY (category_id) REFERENCES category(id)
);



-- Create indexes for better performance
CREATE INDEX idx_expense_receipt_id ON expense(receipt_id);
CREATE INDEX idx_expense_category_id ON expense(category_id);
CREATE INDEX idx_expense_created_at ON expense(created_at);
CREATE INDEX idx_category_created_at ON category(created_at);
CREATE INDEX idx_receipt_created_at ON receipt(created_at);