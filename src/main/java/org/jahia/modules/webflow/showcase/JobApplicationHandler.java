/**
 * This file is part of Jahia, next-generation open source CMS:
 * Jahia's next-generation, open source CMS stems from a widely acknowledged vision
 * of enterprise application convergence - web, search, document, social and portal -
 * unified by the simplicity of web content management.
 *
 * For more information, please visit http://www.jahia.com.
 *
 * Copyright (C) 2002-2014 Jahia Solutions Group SA. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * As a special exception to the terms and conditions of version 2.0 of
 * the GPL (or any later version), you may redistribute this Program in connection
 * with Free/Libre and Open Source Software ("FLOSS") applications as described
 * in Jahia's FLOSS exception. You should have received a copy of the text
 * describing the FLOSS exception, and it is also available here:
 * http://www.jahia.com/license
 *
 * Commercial and Supported Versions of the program (dual licensing):
 * alternatively, commercial and supported versions of the program may be used
 * in accordance with the terms and conditions contained in a separate
 * written agreement between you and Jahia Solutions Group SA.
 *
 * If you are unsure which license is appropriate for your use,
 * please contact the sales department at sales@jahia.com.
 */
package org.jahia.modules.webflow.showcase;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.jcr.AccessDeniedException;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.ItemNotFoundException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jahia.api.Constants;
import org.jahia.modules.webflow.showcase.JobApplication.UploadedFile;
import org.jahia.services.content.JCRCallback;
import org.jahia.services.content.JCRContentUtils;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.content.JCRTemplate;

/**
 * Job application handler that persists the data of the submitted application.
 * 
 * @author Sergiy Shyrkov
 */
public class JobApplicationHandler implements Serializable {

    private static final long serialVersionUID = -4788571325460688293L;

    public void applyForJob(final JobApplication jobApplication, JCRNodeWrapper job) throws RepositoryException {
        try {
            final String jobId = job.getIdentifier();
            JCRTemplate.getInstance().doExecuteWithSystemSession(job.getSession().getUserID(),
                    job.getSession().getWorkspace().getName(), new JCRCallback<Boolean>() {
                        @Override
                        public Boolean doInJCR(JCRSessionWrapper session) throws RepositoryException {

                            saveApplicationData(jobApplication, jobId, session);

                            return Boolean.TRUE;
                        }

                    });
        } finally {
            if (jobApplication.getCoverLetter() != null) {
                FileUtils.deleteQuietly(jobApplication.getCoverLetter().getFile());
            }
            if (jobApplication.getResume() != null) {
                FileUtils.deleteQuietly(jobApplication.getResume().getFile());
            }
        }
    }

    public JCRNodeWrapper getApplication(JCRNodeWrapper job, String applicationId) throws RepositoryException {
        return job.getSession().getNodeByIdentifier(applicationId);
    }

    public List<JCRNodeWrapper> getApplicationsForJob(JCRNodeWrapper job) throws RepositoryException {
        return JCRContentUtils.getNodes(job, "jmix:showcaseJobApplication");
    }

    private void saveApplicationData(final JobApplication jobApplication, final String jobId, JCRSessionWrapper session)
            throws ItemNotFoundException, RepositoryException, ItemExistsException, PathNotFoundException,
            NoSuchNodeTypeException, LockException, VersionException, ConstraintViolationException,
            ValueFormatException, AccessDeniedException, InvalidItemStateException {
        JCRNodeWrapper jobNode = session.getNodeByIdentifier(jobId);

        JCRNodeWrapper applicationNode = jobNode.addNode(JCRContentUtils.findAvailableNodeName(jobNode, "application"),
                "jnt:jobApplication");
        applicationNode.addMixin("jmix:showcaseJobApplication");

        applicationNode.setProperty("firstname", jobApplication.getFirstname());
        applicationNode.setProperty("lastname", jobApplication.getLastname());
        applicationNode.setProperty("email", jobApplication.getEmail());
        applicationNode.setProperty("phone", jobApplication.getPhone());

        applicationNode.setProperty("salary", jobApplication.getSalary());

        if (jobApplication.getStartingDate() != null) {
            applicationNode.setProperty("startingDate",
                    JCRContentUtils.createValue(jobApplication.getStartingDate(), session.getValueFactory()));
        }

        storeFile(applicationNode, "coverLetter", jobApplication.getCoverLetter());
        storeFile(applicationNode, "resume", jobApplication.getResume());

        session.save();
    }

    public void setApplicationStatus(String applicationId, String status) throws RepositoryException {
        JCRSessionWrapper session = JCRSessionFactory.getInstance().getCurrentUserSession(Constants.LIVE_WORKSPACE);
        session.getNodeByIdentifier(applicationId).setProperty("status", status);
        session.save();
    }

    private void storeFile(JCRNodeWrapper applicationNode, String subnodeName, UploadedFile coverLetter)
            throws RepositoryException {
        if (coverLetter != null) {
            InputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(coverLetter.getFile()));
                applicationNode.uploadFile(subnodeName, is,
                        JCRContentUtils.getMimeType(coverLetter.getOriginalFilename(), coverLetter.getContentType()));
            } catch (FileNotFoundException e) {
                throw new RepositoryException(e);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
    }
}
