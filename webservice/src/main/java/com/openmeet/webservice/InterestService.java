package com.openmeet.webservice;

import com.openmeet.shared.data.interest.Interest;
import com.openmeet.shared.data.interest.InterestDAO;
import com.openmeet.shared.data.storage.DAO;
import com.openmeet.shared.helpers.ResponseHelper;
import com.openmeet.webservice.proxies.InterestProxyDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterestService extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String operation = request.getParameter("operation");
        PrintWriter out = response.getWriter();

        //check if all the parameters are present
        if (!ResponseHelper.checkStringFields(operation)) {
            ResponseHelper.sendGenericError(out);
            logger.log(Level.SEVERE, "InterestService:doPost() - Error: missing parameters - operation");
            return;
        }

        InterestDAO interestDAO = new InterestDAO((DataSource) getServletContext().getAttribute("DataSource"));
        InterestProxyDAO interestProxyDAO = new InterestProxyDAO(interestDAO, request, out);

        switch (operation) {
            case DAO.DO_RETRIEVE_BY_CONDITION: {
                try {

                    interestProxyDAO.doRetrieveByCondition(null);
                } catch (SQLException | InvalidParameterException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            case DAO.DO_RETRIEVE_BY_KEY: {

                try {
                    interestProxyDAO.doRetrieveByKey(null);
                } catch (SQLException | InvalidParameterException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            case DAO.DO_RETRIEVE_ALL: {
                try {
                    interestProxyDAO.doRetrieveAll();
                } catch (SQLException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            case DAO.DO_RETRIEVE_ALL_LIMIT: {
                try {
                    interestProxyDAO.doRetrieveAll(0);
                } catch (SQLException | InvalidParameterException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            case DAO.DO_RETRIEVE_ALL_LIMIT_OFFSET: {
                try {
                    interestProxyDAO.doRetrieveAll(0, 0);
                } catch (SQLException | InvalidParameterException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            case DAO.DO_SAVE: {
                try {
                    interestProxyDAO.doSave(new Interest());
                } catch (SQLException | InvalidParameterException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            case DAO.DO_SAVE_PARTIAL:
                E:
                {
                    try {
                        interestProxyDAO.doSave(new HashMap<>());
                    } catch (SQLException | InvalidParameterException e) {
                        ResponseHelper.sendGenericError(out);
                        logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                        return;
                    }
                    break;
                }
            case DAO.DO_UPDATE: {
                try {
                    interestProxyDAO.doUpdate(null, null);
                } catch (SQLException | InvalidParameterException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            case DAO.DO_SAVE_OR_UPDATE: {
                try {
                    interestProxyDAO.doSaveOrUpdate(null);
                } catch (SQLException | InvalidParameterException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            case DAO.DO_DELETE: {
                try {
                    interestProxyDAO.doDelete(null);
                } catch (SQLException | InvalidParameterException e) {
                    ResponseHelper.sendGenericError(out);
                    logger.log(Level.SEVERE, "InterestService:doPost() - Error: " + e.getMessage());
                    return;
                }
                break;
            }
            default:
                ResponseHelper.sendGenericError(out);
                logger.log(Level.SEVERE, "InterestService:doPost() - Error: unknown operation");
                break;
        }
    }

}