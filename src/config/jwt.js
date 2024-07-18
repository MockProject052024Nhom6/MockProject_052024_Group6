import jwt from 'jsonwebtoken';

export const createToken = (data) => {
    let token = jwt.sign({data}, "secret", {algorithm: "HS256", expiresIn: "3m"});
    return token;
}

export const checkToken = (token) => jwt.verify(token, "secret", (error, decoded) => error);

export const decodeToken = (token) => {
    return jwt.decode(token);
}

export const verifyToken = (req, res, next) => {
    let {token} = req.headers;

    let check = checkToken(token);

    if(check == null) {
        next()
    }else {
        res.status(401).send(check.name)
    }
}

export const createRefToken = (data) => {
    let token = jwt.sign({ data }, "No_secret", { algorithm: "HS256", expiresIn: "7d" });

    return token;
}

export const checkRefToken = (token) => jwt.verify(token, "No_secret", (error, decoded) => error
);
