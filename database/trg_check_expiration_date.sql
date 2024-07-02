CREATE OR REPLACE TRIGGER trg_check_expiration_date
BEFORE INSERT OR UPDATE ON ManufacturingDetails
FOR EACH ROW
BEGIN
    IF TRUNC(:NEW.ExpirationDate) < TRUNC(SYSDATE) THEN
         RAISE_APPLICATION_ERROR(-20001, 'Expiration Date cannot be earlier than current date.');
     END IF;
END;
/

